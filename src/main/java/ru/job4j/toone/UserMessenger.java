package ru.job4j.toone;

import javax.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "j_user_notification")
public class UserMessenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Integer id;
    private String messenger;
    private String identify;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "j_user_id")
    private User user;

    public UserMessenger(String messenger, String identify) {
        this.messenger = messenger;
        this.identify = identify;
    }
}