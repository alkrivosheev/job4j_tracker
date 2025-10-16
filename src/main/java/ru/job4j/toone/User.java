package ru.job4j.toone;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import ru.job4j.tracker.Item;

import java.util.List;
import java.util.ArrayList;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "j_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")

    private List<UserMessenger> messengers = new ArrayList<>();

    @ManyToMany(mappedBy = "participates")
    private List<Item> items = new ArrayList<>();

    public void addMessenger(UserMessenger messenger) {
        if (this.messengers == null) {
            this.messengers = new ArrayList<>();
        }
        this.messengers.add(messenger);
        messenger.setUser(this);
    }
}
