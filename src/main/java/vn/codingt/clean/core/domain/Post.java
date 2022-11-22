package vn.codingt.clean.core.domain;

import lombok.Value;

@Value
public class Post {

    Identity id;

    User user;

    String title;

    String mateTitle;

    String slug;

    String summary;

    Boolean published;

    String content;
}
