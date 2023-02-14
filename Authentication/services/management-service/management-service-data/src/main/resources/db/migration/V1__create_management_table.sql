CREATE TABLE ORGANIZATIONINFO(
    id BIGSERIAL PRIMARY KEY,
    organization_info_uuid VARCHAR(256) NOT NULL UNIQUE,
    tenant_id VARCHAR(256) NOT NULL UNIQUE,
    domain_name VARCHAR(256) NOT NULL UNIQUE,
    created_by VARCHAR(36) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(36) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE ORGANIZATIONDATASOURCEINFO(
    id BIGSERIAL PRIMARY KEY,
    organization_datasource_info_uuid VARCHAR(256) NOT NULL UNIQUE,
    tenant_id VARCHAR(256) NOT NULL,
    datasource_type VARCHAR(256) NOT NULL,
    organization_info_fk BIGINT,--FK
    driver_class VARCHAR(256),
    datasource_url VARCHAR(256),
    datasource_username VARCHAR(256) NOT NULL,
    datasource_password VARCHAR(256) NOT NULL,
    host VARCHAR(256) NOT NULL,
    port VARCHAR(64) NOT NULL,
    db_name VARCHAR(256) NOT NULL ,
    ssl_enable BOOLEAN DEFAULT TRUE,
    created_by VARCHAR(36) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(36) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    CONSTRAINT organization_info_fk_constraint FOREIGN KEY(organization_info_fk) REFERENCES OrganizationInfo(id)
);