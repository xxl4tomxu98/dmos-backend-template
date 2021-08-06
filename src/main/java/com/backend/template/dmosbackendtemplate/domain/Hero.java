package com.backend.template.dmosbackendtemplate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "heroes")
@NamedQuery(name = "Hero.findAll", query="select h from Hero h")
public class Hero
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hero_id", nullable = false, unique = true)
    private Long heroId;

    @NotNull
    @Column(name = "hero_name", nullable = false, unique = true)
    private String heroName;

}
