package core;
import java.util.Optional;
import java.util.UUID;

public class Entity{
    public final String id;
    public final Long createdAt;
    public Long updatedAt;

    protected Entity(EntityProps entityProps) {
        long now = System.currentTimeMillis();

        this.id = Optional.ofNullable(entityProps.id).orElse(UUID.randomUUID().toString());
        this.createdAt = Optional.ofNullable(entityProps.createdAt).orElse(now);
        this.updatedAt = Optional.ofNullable(entityProps.updatedAt).orElse(now);
    }
}