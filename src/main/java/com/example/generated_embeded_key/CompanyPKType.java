package com.example.generated_embeded_key;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.EnhancedUserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class CompanyPKType implements EnhancedUserType<CompanyPk> {
    @Override
    public int getSqlType() {
        return Types.VARCHAR;
    }

    @Override
    public Class<CompanyPk> returnedClass() {
        return CompanyPk.class;
    }

    @Override
    public boolean equals(CompanyPk companyPk, CompanyPk j1) {
        return companyPk.equals(j1);
    }

    @Override
    public int hashCode(CompanyPk companyPk) {
        return companyPk.hashCode();
    }

    @Override
    public CompanyPk nullSafeGet(ResultSet resultSet, int position, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        String value = resultSet.getString(position);
        CompanyPk companyPk = new CompanyPk(value);

        return companyPk;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, CompanyPk value, int index, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        if (Objects.isNull(value) || value.getId() == null)
            preparedStatement.setNull(index, Types.VARCHAR);
        else {
            String longVal = value.getId();
            preparedStatement.setString(index, longVal);
        }
    }

    @Override
    public CompanyPk deepCopy(CompanyPk companyPk) {
        return new CompanyPk(companyPk.getId());
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(CompanyPk companyPk) {
        return companyPk;
    }

    @Override
    public CompanyPk assemble(Serializable serializable, Object o) {
        return (CompanyPk) serializable;
    }

    @Override
    public String toSqlLiteral(CompanyPk companyPk) {
        return '\'' + String.valueOf(companyPk.getId()) + '\'';
    }

    @Override
    public String toString(CompanyPk companyPk) throws HibernateException {
        return String.valueOf(companyPk);
    }

    @Override
    public CompanyPk fromStringValue(CharSequence charSequence) throws HibernateException {
        return new CompanyPk(charSequence.toString());
    }
}
