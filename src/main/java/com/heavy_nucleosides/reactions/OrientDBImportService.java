package com.heavy_nucleosides.reactions;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrientDBImportService implements ImportService{

    private static String workDir;
    private static ArrayList<String> files;
    static {
        workDir = System.getProperty("user.dir");
        files = new ArrayList<>();
        files.add(workDir);
    }
    @Override
    public String imp() {
        String res = "ok";
        try {
            OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
            ODatabaseSession db = orient.open("reactions", "root", "1");

            OClass component = db.getClass("Component");

            if (db.getClass("Component") == null) {
                db.createVertexClass("Component");
            }

            if (component.getProperty("name") == null) {
                component.createProperty("name", OType.STRING);
                component.createIndex("Component_name_index", OClass.INDEX_TYPE.NOTUNIQUE, "name");
            }

            OClass reaction = db.getClass("Reaction");

            if (db.getClass("Reaction") == null) {
                db.createVertexClass("Reaction");
            }

            if (reaction.getProperty("name") == null) {
                reaction.createProperty("name", OType.STRING);
                reaction.createIndex("Reaction_name_index", OClass.INDEX_TYPE.NOTUNIQUE, "name");
            }

            if (db.getClass("include") == null) {
                db.createEdgeClass("include");
            }

            if (db.getClass("produce") == null) {
                db.createEdgeClass("produce");
            }

            orient.close();
        } catch (Exception e) {
            e.printStackTrace();
            res = e.getMessage();
        }

        return res;
    }

    @Override
    public String imp_file(String filename) {
        return  "import: " + filename;
    }

    @Override
    public List<String> list() {
        return files;
    }
}
