/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.ecm.domain;

@SuppressWarnings("ucd")
public class ContentActivityLogWithBLOBs extends ContentActivityLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_activity_log.actionDesc
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("actionDesc")
    private String actiondesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_activity_log.baseFolderPath
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("baseFolderPath")
    private String basefolderpath;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_activity_log.actionDesc
     *
     * @return the value of m_ecm_activity_log.actionDesc
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public String getActiondesc() {
        return actiondesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_activity_log.actionDesc
     *
     * @param actiondesc the value for m_ecm_activity_log.actionDesc
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setActiondesc(String actiondesc) {
        this.actiondesc = actiondesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_activity_log.baseFolderPath
     *
     * @return the value of m_ecm_activity_log.baseFolderPath
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public String getBasefolderpath() {
        return basefolderpath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_activity_log.baseFolderPath
     *
     * @param basefolderpath the value for m_ecm_activity_log.baseFolderPath
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setBasefolderpath(String basefolderpath) {
        this.basefolderpath = basefolderpath;
    }

    public static enum Field {
        id,
        createduser,
        createdtime,
        createduserfullname,
        actiondesc,
        basefolderpath;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}