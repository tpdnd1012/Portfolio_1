package Spring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    int id;

    @Column(name = "userid", length = 45)
    String userid;

    @Column(name = "password", length = 45)
    String password;

    @Builder
    public UserEntity(int id, String userid, String password) {
        this.id = id;
        this.userid = userid;
        this.password = password;
    }

}
