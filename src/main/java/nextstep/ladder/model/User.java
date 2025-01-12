package nextstep.ladder.model;

import java.util.Objects;

public final class User {

    private final UserName name;

    public User(UserName name) {
        validate(name);
        this.name = name;
    }

    private void validate(UserName name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("전달된 사용자 이름이 null 입니다.");
        }
    }

    public String getName() {
        return name.getName();
    }

    public boolean hasName(UserName name) {
        return this.name.equals(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
