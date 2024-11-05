package org.example.coursework_orm.dao;

import org.example.coursework_orm.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)? daoFactory = new DAOFactory(): daoFactory;
    }

    public enum DAOTypes{
        CULINARY_PROGRAM,STUDENTS,ADMISSION_COORDINATOR,ADMIN,ADMIN_SIGN_IN,ADMISSION_COORDINATOR_SIGN_IN,RESET_USERNAME_PASSWORD_ADMIN,RESET_USERNAME_PASSWORD_ADMISSION_COORDINATOR
    }

    public SuperDAO getDao(DAOTypes types){
        switch (types){
            case CULINARY_PROGRAM:
                return new Culinary_ProgramsDAOImpl();

            case STUDENTS:
                return new StudentsDAOImpl();

            case ADMISSION_COORDINATOR:
                return new AdmissionCoordinatorDAOImpl();

            case ADMIN:
                return new AdminDAOImpl();

            case ADMIN_SIGN_IN:
                return new AdminSignInDAOImpl();

            case ADMISSION_COORDINATOR_SIGN_IN:
                return new AdmissionCoordinatorSignInDAOImpl();

            case RESET_USERNAME_PASSWORD_ADMIN:
                return new ResetUsernamePasswordAdminDAOImpl();

            case RESET_USERNAME_PASSWORD_ADMISSION_COORDINATOR:
                return new ResetUsernamePasswordAdmissionCoordinatorDAOImpl();

            default:
                return null;
        }
    }

}
