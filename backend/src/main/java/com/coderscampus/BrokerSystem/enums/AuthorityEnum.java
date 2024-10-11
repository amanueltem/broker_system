package com.coderscampus.BrokerSystem.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthorityEnum {
    ROLE_STUDENT("ROLE_STUDENT","Student"),
    ROLE_EMPLOYEE("ROLE_EMPLOYEE","Employee"),
    ROLE_LIBRARY("ROLE_LIBRARY","Library Circulation"),
    ROLE_REGISTRAR("ROLE_REGISTRAR","Registrar"),
    ROLE_CAFETERIA("ROLE_CAFETERIA","Student Cafeteria"),
    ROLE_DEPARTMENT_HEAD("ROLE_DEPARTMENT_HEAD","Department Head"),
    ROLE_COLLEGE_DEAN("ROLE_COLLEGE_DEAN","College Dean"),
    ROLE_CAMPUS_POLICE("ROLE_CAMPUS_POLICE","Campus Police"),
    ROLE_HR("ROLE_HR","HR"),
    ROLE_ADMIN("ROLE_ADMIN","Admin"),
    ROLE_PROCTOR("ROLE_PROCTOR","Proctor");
    private String roleName;
    private String roleValue;
    AuthorityEnum(String roleName,String roleValue){
        this.roleName=roleName;
        this.roleValue=roleValue;
    }
    public String getRoleName(){
        return roleName;
    }
    public String getRoleValue(){
        return roleValue;
    }
}

