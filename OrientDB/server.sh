docker run -d --name orientdb -p 2424:2424 -p 2480:2480 \
    -v config:/orientdb/config \
    -v databases:/orientdb/databases \
    -v backup:/orientdb/backup \
    -e ORIENTDB_ROOT_PASSWORD=rootpwd \
    orientdb