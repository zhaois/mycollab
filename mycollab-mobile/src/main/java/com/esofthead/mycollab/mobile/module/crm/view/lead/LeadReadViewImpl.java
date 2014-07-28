/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.module.crm.view.lead;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.form.view.DynaFormLayout;
import com.esofthead.mycollab.mobile.module.crm.events.LeadEvent;
import com.esofthead.mycollab.mobile.module.crm.ui.AbstractPreviewItemComp;
import com.esofthead.mycollab.mobile.module.crm.ui.CrmPreviewFormControlsGenerator;
import com.esofthead.mycollab.mobile.module.crm.ui.CrmRelatedItemsScreenData;
import com.esofthead.mycollab.mobile.module.crm.ui.NotesList;
import com.esofthead.mycollab.mobile.module.crm.view.activity.ActivityRelatedItemView;
import com.esofthead.mycollab.mobile.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.mobile.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.mobile.ui.IconConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import com.esofthead.mycollab.module.crm.i18n.CrmCommonI18nEnum;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */

@ViewComponent
public class LeadReadViewImpl extends AbstractPreviewItemComp<SimpleLead>
		implements LeadReadView {
	private static final long serialVersionUID = 5288751461504873888L;
	private NotesList associateNotes;
	private ActivityRelatedItemView associateActivities;
	private LeadRelatedCampaignView associateCampaigns;

	@Override
	public HasPreviewFormHandlers<SimpleLead> getPreviewFormHandlers() {
		return this.previewForm;
	}

	@Override
	protected void initRelatedComponents() {
		associateCampaigns = new LeadRelatedCampaignView();
		associateNotes = new NotesList("Related Notes");
		associateActivities = new ActivityRelatedItemView();
	}

	@Override
	protected void onPreviewItem() {
		associateCampaigns.displayCampaign(beanItem);
		associateNotes.showNotes(CrmTypeConstants.LEAD, beanItem.getId());
		final ActivitySearchCriteria searchCriteria = new ActivitySearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		searchCriteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.LEAD));
		searchCriteria.setTypeid(new NumberSearchField(SearchField.AND,
				beanItem.getId()));
		associateActivities.setSearchCriteria(searchCriteria);
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getLeadName();
	}

	@Override
	protected AdvancedPreviewBeanForm<SimpleLead> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleLead>();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new DynaFormLayout(CrmTypeConstants.LEAD,
				LeadDefaultDynaFormLayoutFactory.getForm());
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleLead> initBeanFormFieldFactory() {
		return new LeadReadFormFieldFactory(previewForm);
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return new CrmPreviewFormControlsGenerator<SimpleLead>(this.previewForm)
				.createButtonControls(RolePermissionCollections.CRM_LEAD);
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		HorizontalLayout toolbarLayout = new HorizontalLayout();
		toolbarLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		toolbarLayout.setSpacing(true);

		Button relatedCampaigns = new Button();
		relatedCampaigns.setCaption("<span aria-hidden=\"true\" data-icon=\""
				+ IconConstants.CRM_CAMPAIGN
				+ "\"></span><div class=\"screen-reader-text\">"
				+ AppContext.getMessage(CrmCommonI18nEnum.TAB_CAMPAIGN)
				+ "</div>");
		relatedCampaigns.setHtmlContentAllowed(true);
		relatedCampaigns.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7027681785871215444L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBusFactory.getInstance().post(
						new LeadEvent.GoToRelatedItems(LeadReadViewImpl.this,
								new CrmRelatedItemsScreenData(
										associateCampaigns)));
			}
		});
		toolbarLayout.addComponent(relatedCampaigns);

		Button relatedNotes = new Button();
		relatedNotes.setCaption("<span aria-hidden=\"true\" data-icon=\""
				+ IconConstants.CRM_DOCUMENT
				+ "\"></span><div class=\"screen-reader-text\">"
				+ AppContext.getMessage(CrmCommonI18nEnum.TAB_NOTE) + "</div>");
		relatedNotes.setHtmlContentAllowed(true);
		relatedNotes.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7589415773039335559L;

			@Override
			public void buttonClick(ClickEvent arg0) {
				EventBusFactory.getInstance().post(
						new LeadEvent.GoToRelatedItems(this,
								new CrmRelatedItemsScreenData(associateNotes)));
			}
		});
		toolbarLayout.addComponent(relatedNotes);

		Button relatedActivities = new Button();
		relatedActivities.setCaption("<span aria-hidden=\"true\" data-icon=\""
				+ IconConstants.CRM_ACTIVITY
				+ "\"></span><div class=\"screen-reader-text\">"
				+ AppContext.getMessage(CrmCommonI18nEnum.TAB_ACTIVITY)
				+ "</div>");
		relatedActivities.setHtmlContentAllowed(true);
		relatedActivities.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7589415773039335559L;

			@Override
			public void buttonClick(ClickEvent arg0) {
				EventBusFactory.getInstance().post(
						new LeadEvent.GoToRelatedItems(this,
								new CrmRelatedItemsScreenData(
										associateActivities)));
			}
		});
		toolbarLayout.addComponent(relatedActivities);

		return toolbarLayout;
	}

}
