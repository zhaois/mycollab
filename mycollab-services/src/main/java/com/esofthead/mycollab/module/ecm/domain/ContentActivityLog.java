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
/*Domain class of table m_ecm_activity_log*/
package com.esofthead.mycollab.module.ecm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import java.util.Date;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("m_ecm_activity_log")
class ContentActivityLog extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_activity_log.id
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_activity_log.createdUser
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_activity_log.createdTime
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_activity_log.createdUserFullName
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("createdUserFullName")
    private String createduserfullname;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_activity_log.id
     *
     * @return the value of m_ecm_activity_log.id
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_activity_log.id
     *
     * @param id the value for m_ecm_activity_log.id
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_activity_log.createdUser
     *
     * @return the value of m_ecm_activity_log.createdUser
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_activity_log.createdUser
     *
     * @param createduser the value for m_ecm_activity_log.createdUser
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_activity_log.createdTime
     *
     * @return the value of m_ecm_activity_log.createdTime
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_activity_log.createdTime
     *
     * @param createdtime the value for m_ecm_activity_log.createdTime
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_activity_log.createdUserFullName
     *
     * @return the value of m_ecm_activity_log.createdUserFullName
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public String getCreateduserfullname() {
        return createduserfullname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_activity_log.createdUserFullName
     *
     * @param createduserfullname the value for m_ecm_activity_log.createdUserFullName
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setCreateduserfullname(String createduserfullname) {
        this.createduserfullname = createduserfullname;
    }
}