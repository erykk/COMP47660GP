package ie.ucd.COMP47660GP.entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
