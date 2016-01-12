package jp.blackawa.model;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;

import java.util.Date;
import java.util.UUID;

@IQTable(name = "stems")
public class Stem {
    @IQColumn(name = "id", primaryKey = true)
    public UUID id;

    @IQColumn(name = "name")
    public String name;

    @IQColumn(name = "create_user_id")
    public UUID createUserId;

    @IQColumn(name = "created_time")
    public Date createdTime;

    @IQColumn(name = "update_user_id")
    public UUID updateUserId;

    @IQColumn(name = "updated_time")
    public Date updatedTime;
}
