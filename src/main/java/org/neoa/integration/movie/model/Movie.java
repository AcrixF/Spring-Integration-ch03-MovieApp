package org.neoa.integration.movie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Movie {
    private String title;
    private String actor;
    private int year;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Movie(title: ")
                .append(title)
                .append(", actor: ")
                .append(actor)
                .append(", year: ")
                .append(year)
                .append(")");
        return builder.toString();
    }
}
