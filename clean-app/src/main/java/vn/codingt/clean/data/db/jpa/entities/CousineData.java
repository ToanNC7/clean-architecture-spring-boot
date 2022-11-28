package vn.codingt.clean.data.db.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.codingt.clean.core.domain.Cousine;
import vn.codingt.clean.core.domain.Identity;

@AllArgsConstructor
@Entity(name = "cousine")
@EqualsAndHashCode(of = { "name" })
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cousine")
@ToString(of = { "name" })
public class CousineData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    public static CousineData newInstance(String name) {
        return new CousineData(null, name);
    }

    public static CousineData from(Cousine cousine) {
        return new CousineData(
                IdConverter.converterId(cousine.getId()),
                cousine.getName());
    }

    public Cousine fromThis() {
        return new Cousine(
                new Identity(id),
                name);
    }
}
