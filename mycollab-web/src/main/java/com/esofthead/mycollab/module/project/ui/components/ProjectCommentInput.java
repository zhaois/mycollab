/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.domain.CommentWithBLOBs;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AttachmentPanel;
import com.esofthead.mycollab.vaadin.ui.ReloadableComponent;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.vaadin.easyuploads.MultiFileUploadExt;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.util.GregorianCalendar;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProjectCommentInput extends MHorizontalLayout {
	private static final long serialVersionUID = 1L;
	private final RichTextArea commentArea;

	private CommentType type;
	private String typeId;
	private Integer extraTypeId;

	ProjectCommentInput(
			final ReloadableComponent component,
			final CommentType typeVal,
			final Integer extraTypeIdVal,
			final boolean cancelButtonEnable,
			final boolean isSendingEmailRelay,
			final Class<? extends SendingRelayEmailNotificationAction> emailHandler) {
		super();
		this.withWidth("100%").withStyleName("message");

		final SimpleUser currentUser = AppContext.getSession();
		MVerticalLayout userBlock = new MVerticalLayout().withMargin(false).withWidth("80px");
		userBlock.setDefaultComponentAlignment(Alignment.TOP_CENTER);

		ClickListener gotoUser = new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBusFactory.getInstance().post(
						new ProjectMemberEvent.GotoRead(this, currentUser
								.getUsername()));
			}
		};

		Button userAvatarBtn = UserAvatarControlFactory
				.createUserAvatarButtonLink(currentUser.getAvatarid(),
						currentUser.getDisplayName());
		userAvatarBtn.addClickListener(gotoUser);
		userBlock.addComponent(userAvatarBtn);
		Button userName = new Button(currentUser.getDisplayName());
		userName.setStyleName("user-name");
		userName.addStyleName("link");
		userName.addStyleName(UIConstants.WORD_WRAP);
		userName.addClickListener(gotoUser);
		userBlock.addComponent(userName);

		this.addComponent(userBlock);
		MVerticalLayout textAreaWrap = new MVerticalLayout().withWidth("100%")
				.withStyleName("message-container");
		this.addComponent(textAreaWrap);
		this.setExpandRatio(textAreaWrap, 1.0f);

		type = typeVal;
		extraTypeId = extraTypeIdVal;

		commentArea = new RichTextArea();
		commentArea.setWidth("100%");
		commentArea.setHeight("200px");

		final AttachmentPanel attachments = new AttachmentPanel();

		final MHorizontalLayout controlsLayout = new MHorizontalLayout().withSpacing(true).withMargin(false)
				.withWidth("100%");

		final MultiFileUploadExt uploadExt = new MultiFileUploadExt(attachments);
		uploadExt.addComponent(attachments);
		controlsLayout.with(uploadExt).withAlign(uploadExt, Alignment.TOP_LEFT).expand(uploadExt);

		final Label emptySpace = new Label();
		controlsLayout.addComponent(emptySpace);
		controlsLayout.setExpandRatio(emptySpace, 1.0f);

		if (cancelButtonEnable) {
			final Button cancelBtn = new Button(
					AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							component.cancel();
						}
					});
			cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
			controlsLayout.addComponent(cancelBtn);
			controlsLayout.setComponentAlignment(cancelBtn,
					Alignment.TOP_RIGHT);
		}

		final Button newCommentBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_POST),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final Button.ClickEvent event) {
						final CommentWithBLOBs comment = new CommentWithBLOBs();
						comment.setComment(Jsoup.clean(commentArea.getValue(),
								Whitelist.relaxed()));
						comment.setCreatedtime(new GregorianCalendar()
								.getTime());
						comment.setCreateduser(AppContext.getUsername());
						comment.setSaccountid(AppContext.getAccountId());
						comment.setType(type.toString());
						comment.setTypeid("" + typeId);
						comment.setExtratypeid(extraTypeId);

						final CommentService commentService = ApplicationContextUtil
								.getSpringBean(CommentService.class);
						int commentId = commentService.saveWithSession(comment,
								AppContext.getUsername(), isSendingEmailRelay,
								emailHandler);

						String attachmentPath = AttachmentUtils
								.getProjectEntityCommentAttachmentPath(typeVal,
										AppContext.getAccountId(),
										CurrentProjectVariables.getProjectId(),
										typeId, commentId);

						if (!"".equals(attachmentPath)) {
							attachments.saveContentsToRepo(attachmentPath);
						}

						// save success, clear comment area and load list
						// comments again
						commentArea.setValue("");
						attachments.removeAllAttachmentsDisplay();
						component.reload();
					}
				});
		newCommentBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		newCommentBtn.setIcon(FontAwesome.SEND);
		controlsLayout.with(newCommentBtn).withAlign(newCommentBtn, Alignment.TOP_RIGHT);

		textAreaWrap.addComponent(commentArea);
		textAreaWrap.addComponent(controlsLayout);
	}

	void setTypeAndId(final String typeId) {
		this.typeId = typeId;
	}
}
