CREATE TABLE TRS_ROLE(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    role_uuid VARCHAR(256) NOT NULL,
    role_name VARCHAR(256) NOT NULL,
    description TEXT,
    created_by VARCHAR(36) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(36) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    system VARCHAR(256) NOT NULL
);

CREATE TABLE TRS_USER(
    id BIGSERIAL PRIMARY KEY,
    user_uuid VARCHAR(256) NOT NULL UNIQUE,
    role_id_fk BIGINT NOT NULL,
    first_name VARCHAR(256) NOT NULL,
    last_name VARCHAR(256),
    validation_code INT,
    mfa_secret_key VARCHAR(256),
    email VARCHAR(256) NOT NULL UNIQUE,
    enable_mfa BOOLEAN DEFAULT FALSE,
    created_by VARCHAR(256) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(256) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    CONSTRAINT role_id_fk_constraint FOREIGN KEY(role_id_fk) REFERENCES TRS_ROLE(id)
);

CREATE TABLE user_credential(
    id BIGSERIAL PRIMARY KEY,
    user_credential_uuid VARCHAR(256) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    password_salt VARCHAR(256),
    user_id_fk BIGINT NOT NULL,
    created_by VARCHAR(256) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(256) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    CONSTRAINT user_id_fk_constraint FOREIGN KEY(user_id_fk) REFERENCES TRS_ROLE(id)
);

CREATE TABLE token(
    id BIGSERIAL PRIMARY KEY,
    token_uuid VARCHAR(256) NOT NULL UNIQUE,
    token VARCHAR(256) NOT NULL,
    user_id_fk BIGINT NOT NULL,
    type VARCHAR(256) DEFAULT 'JWT' CHECK (type IN ('JWT')),
    created_by VARCHAR(256) DEFAULT 'SYSTEM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(256) DEFAULT 'SYSTEM',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    CONSTRAINT user_id_token_fk_constraint FOREIGN KEY(user_id_fk) REFERENCES TRS_USER(id)
);

CREATE TABLE SCRATCHCODES(
    id BIGSERIAL PRIMARY KEY,
    scratch_code_uuid VARCHAR(256),
    user_id_fk BIGINT NOT NULL,
    scratch_code INT NOT NULL,
    CONSTRAINT user_id_scratchcode_fk_constraint FOREIGN KEY(user_id_fk) REFERENCES TRS_USER(id)
)