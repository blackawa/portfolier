package jp.blackawa.model;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;

@IQTable(name = "users")
public class User {

    public User () {
        super();
    }

    public User (Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @IQColumn(primaryKey = true)
    public Long id;

    @IQColumn(length = 60, trim = true)
    public String name;
}
