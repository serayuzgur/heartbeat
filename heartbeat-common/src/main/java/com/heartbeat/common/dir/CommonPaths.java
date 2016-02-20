package com.heartbeat.common.dir;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Common Paths needed in every day development.
 */
public final class CommonPaths {

    /**
     * Returns the parent directory of the jar or main project directory.
     *
     * @param clazz The class which will decide jar or project.
     * @return
     */
    public static final File getApplicationDirectory(Class clazz) {
        File path = null;
        try {
            //Get class directory.
            path = new File(clazz.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            if (path.exists()) {
                if (path.isFile()) {
                    //If it is in jar, get the parent
                    return path.getParentFile();
                } else {
                    //If directory it means it is running in IDE (Its just for easy development)
                    return path.getParentFile().getParentFile();
                }
            }
        } catch (URISyntaxException e) {
            //Impossible exception. Do nothing.
            e.printStackTrace();
        }
        return path;
    }
}
