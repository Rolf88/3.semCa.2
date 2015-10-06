package entity;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Company extends InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    private String cvr;

    private int numEmployees;

    private int marketValue;
}
