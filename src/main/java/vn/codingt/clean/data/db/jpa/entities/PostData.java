package vn.codingt.clean.data.db.jpa.entities;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.codingt.clean.core.domain.Identity;
import vn.codingt.clean.core.domain.Post;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Table(name = "t_post")
@Entity(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserData user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String mateTitle;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private Boolean published;

    @Column(nullable = false)
    private String content;

    @OneToMany(
            mappedBy = "post",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    private Set<MetaData> meta;


    public static PostData newInstance(String title, String mateTitle, String slug, String summary, String content){
        return new PostData(null,
                new UserData(),
                title,
                mateTitle,
                slug,
                summary,
                true,
                content,
                new HashSet<>());
    }

    public static PostData from(Post post){
        return new PostData(
                IdConverter.converterId(post.getId()),
                UserData.from(post.getUser()),
                post.getTitle(),
                post.getMateTitle(),
                post.getSlug(),
                post.getSummary(),
                post.getPublished(),
                post.getContent(),
                new HashSet<>()
        );
    }

    public Post fromThis(){
        return new Post(
                new Identity(id),
                user.fromThis(),
                title,
                mateTitle,
                slug,
                summary,
                published,
                content
        );
    }
}
