// Copyright 2023 The Casdoor Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing CasdoorPermissions and
// limitations under the License.

package org.casbin.casdoor.entity;

import java.util.List;

public class Syncer {
    public String owner;
    public String name;
    public String createdTime;

    public String organization;
    public String type;

    public String host;
    public int port;
    public String user;
    public String password;
    public String databaseType;
    public String database;
    public String table;
    public String tablePrimaryKey;
    public List<TableColumn> tableColumns;
    public String affiliationTable;
    public String avatarBaseUrl;
    public String errorText;
    public int syncInterval;
    public boolean isReadOnly;
    public boolean isEnabled;

    public Syncer() {
    }

    public Syncer(String owner, String name, String createdTime, String organization, String host, int port, String user, String password, String databaseType, String database, String table, int syncInterval) {
        this.owner = owner;
        this.name = name;
        this.createdTime = createdTime;
        this.organization = organization;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.databaseType = databaseType;
        this.database = database;
        this.table = table;
        this.syncInterval = syncInterval;
    }
}
