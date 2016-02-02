package jp.blackawa.model;

import java.util.UUID;

public abstract class AbstractModel {
    public abstract UUID getId();
    public abstract void setId(UUID id);
}
