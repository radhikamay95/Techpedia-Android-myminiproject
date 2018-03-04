package cit.mini.tp.techpedia;

import java.io.Serializable;

/**
 * Created by Saradha on 10-12-2017.
 */

class UserModel implements Serializable {

    private String user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile_number;
    private String college_name;
    private String department_name;
    private String student_id;
    private String  study_year;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }
   public String getDepartment_name(){
        return department_name;
   }

    public void setDepartment_name (String department_name) {
        this.department_name = department_name;
    }
    public String getStudent_id(){
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }


    public String getStudy_year() {
        return study_year;
    }

    public void setStudy_year(String study_year) {
        this.study_year = study_year;
    }


}
