
# -- steps of creation a new hubtangleadmin super-user on a fresh postgres database --
# passwd postgres
# su postgres
# createuser -s hubtangleadmin

dropHubtangleDb() {
    dropdb -U hubtangleadmin hubtangledb   
}

dropHubtangleUser() {
    # uncomment this if you want to delete user before database remove
    #echo "REVOKE ALL ON DATABASE hubtangledb FROM hubtangle" | psql -U hubtangleadmin hubtangledb
    dropuser -U hubtangleadmin hubtangle
}

createHubtangleDb() {
    createdb -U hubtangleadmin hubtangledb    
}

createHubtangleUser() {
    # using user hubtangleadmin create user hubtangle with simple privilages
    createuser -U hubtangleadmin -S -R -D hubtangle

    # grant creation privilages (needed by "create schema")
    echo "GRANT ALL PRIVILEGES ON DATABASE hubtangledb to hubtangle" | psql -U hubtangleadmin hubtangledb
    echo "ALTER ROLE hubtangle with login;" | psql -U hubtangleadmin hubtangledb

}

applySqlPatch() {
    echo "  Applying patch $1"
    psql -U hubtangle hubtangledb < "$1"
}

echo "Recreate hubtangledb started.."

# First, drop all if needed
dropHubtangleDb
dropHubtangleUser

# Second create fresh db and user
createHubtangleDb
createHubtangleUser

#echo "Applying initial SQL scripts.."
#applySqlPatch "src/main/sql/hubtangledb_init.sql"
#applySqlPatch "src/main/sql/testdata/01-two-simple-hubs.sql"
echo "..done"
