package com.yaminsky.bankspringhibernate.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "logs", schema = "marko_yaminsky", catalog = "")
public class Logs implements Serializable {
    @Column
    @Id
    private String time;
    @Column
    @Id
    private String event;
}
