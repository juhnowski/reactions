docker run --name orientdb -p 2424:2424 -p 2480:2480 \
    -v /home/ilya/reactions_OrientDB/databases:/orientdb/databases \
    -e ORIENTDB_ROOT_PASSWORD=root \
    orientdb:latest