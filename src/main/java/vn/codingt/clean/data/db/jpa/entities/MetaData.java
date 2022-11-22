package vn.codingt.clean.data.db.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.codingt.clean.core.domain.Identity;
import vn.codingt.clean.core.domain.Meta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity(name = "meta")
@Table(name = "t_meta")
public class MetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostData post;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private  String content;


    public static MetaData newInstance(String key, String content){
        return new MetaData(null, new PostData(), key, content);
    }

    public static MetaData from(Meta meta){
        return new MetaData(IdConverter.converterId(meta.getId()),
                PostData.from(meta.getPost()),
                meta.getKey(),
                meta.getContent());
    }

    public  Meta fromThis(){
        return  new Meta(
                new Identity(id),
                post.fromThis(),
                key,
                content);
    }
}
