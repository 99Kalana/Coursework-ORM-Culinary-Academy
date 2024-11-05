package org.example.coursework_orm.bo;

import org.example.coursework_orm.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory = new BOFactory():boFactory;
    }

    public enum BOTypes{
        CULINARY_PROGRAM,STUDENTS,ADMISSION_COORDINATOR,ADMIN,ADMIN_SIGN_IN,ADMISSION_COORDINATOR_SIGN_IN,RESET_USERNAME_PASSWORD_ADMIN,RESET_USERNAME_PASSWORD_ADMISSION_COORDINATOR
    }

    public SuperBO getBo(BOTypes types){
        switch (types){

            case CULINARY_PROGRAM:
                return new Culinary_ProgramsBOImpl();

            case STUDENTS:
                return new StudentsBOImpl();

            case ADMISSION_COORDINATOR:
                return new AdmissionCoordinatorBOImpl();

            case ADMIN:
                return new AdminBOImpl();

            case ADMIN_SIGN_IN:
                return new AdminSignInBOImpl();

            case ADMISSION_COORDINATOR_SIGN_IN:
                return new AdmissionCoordinatorSignInBOImpl();

            case RESET_USERNAME_PASSWORD_ADMIN:
                return new ResetUsernamePasswordAdminBOImpl();

            case RESET_USERNAME_PASSWORD_ADMISSION_COORDINATOR:
                return new ResetUsernamePasswordAdmissionCoordinatorBOImpl();

            default:
                return null;
        }
    }

}
