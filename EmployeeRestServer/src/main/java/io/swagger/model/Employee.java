package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Employee
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-01T07:53:57.994Z")

public class Employee   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("MiddleInitial")
  private String middleInitial = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("DateOfBirth")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date dateOfBirth = null;

  @JsonProperty("DateOfEmployment")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date dateOfEmployment = null;

  @JsonProperty("Status")
  private Integer status = null;

  public Employee id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Employee firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

   /**
   * Get firstName
   * @return firstName
  **/
  @ApiModelProperty(value = "")


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Employee middleInitial(String middleInitial) {
    this.middleInitial = middleInitial;
    return this;
  }

   /**
   * Get middleInitial
   * @return middleInitial
  **/
  @ApiModelProperty(value = "")


  public String getMiddleInitial() {
    return middleInitial;
  }

  public void setMiddleInitial(String middleInitial) {
    this.middleInitial = middleInitial;
  }

  public Employee lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

   /**
   * Get lastName
   * @return lastName
  **/
  @ApiModelProperty(value = "")


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Employee dateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
    return this;
  }

   /**
   * Get dateOfBirth
   * @return dateOfBirth
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Employee dateOfEmployment(Date dateOfEmployment) {
    this.dateOfEmployment = dateOfEmployment;
    return this;
  }

   /**
   * Get dateOfEmployment
   * @return dateOfEmployment
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Date getDateOfEmployment() {
    return dateOfEmployment;
  }

  public void setDateOfEmployment(Date dateOfEmployment) {
    this.dateOfEmployment = dateOfEmployment;
  }

  public Employee status(Integer status) {
    this.status = status;
    return this;
  }

   /**
   * Employee Status
   * @return status
  **/
  @ApiModelProperty(value = "Employee Status")


  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Employee employee = (Employee) o;
    return Objects.equals(this.id, employee.id) &&
        Objects.equals(this.firstName, employee.firstName) &&
        Objects.equals(this.middleInitial, employee.middleInitial) &&
        Objects.equals(this.lastName, employee.lastName) &&
        Objects.equals(this.dateOfBirth, employee.dateOfBirth) &&
        Objects.equals(this.dateOfEmployment, employee.dateOfEmployment) &&
        Objects.equals(this.status, employee.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, middleInitial, lastName, dateOfBirth, dateOfEmployment, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Employee {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    middleInitial: ").append(toIndentedString(middleInitial)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    dateOfBirth: ").append(toIndentedString(dateOfBirth)).append("\n");
    sb.append("    dateOfEmployment: ").append(toIndentedString(dateOfEmployment)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  /**
   * Copy non-null attributes
   */
  public void merge(Employee e) {
        if (e.firstName != null) this.firstName = e.firstName;
        if (e.middleInitial != null) this.middleInitial = e.middleInitial;
        if (e.lastName != null) this.lastName = e.lastName;
        if (e.dateOfBirth != null) this.dateOfBirth = e.dateOfBirth;
        if (e.dateOfEmployment != null) this.dateOfEmployment = e.dateOfEmployment;
  }

    public boolean checkIfValid() {
       if (this.dateOfEmployment == null || 
          this.dateOfBirth == null || 
          this.status == 0)
            return false;
       else
           return true;
    }
}

   


