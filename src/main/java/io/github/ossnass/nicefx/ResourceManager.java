package io.github.ossnass.nicefx;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.Resource;
import io.github.classgraph.ResourceList;
import io.github.classgraph.ScanResult;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class allows the loading of resources stored inside the application using ClasssGraph library
 */
public class ResourceManager {
    private static final ResourceManager INSTANCE = new ResourceManager();


    private ResourceManager() {
    }

    /**
     * Loads a resource from the class path based on its URL.
     *
     * @param urlStr the URL of the resource
     * @return a {@link Resource} object from the ClassGraph library representing the resource
     */
    public static Resource getResource(String urlStr) {
        try (ScanResult scan = new ClassGraph().scan()) {
            if (urlStr.startsWith("/"))
                urlStr = urlStr.substring(1);
            ResourceList rl = scan.getResourcesWithPath(urlStr);
            if (rl.size() > 0)
                return rl.get(0);
        }
        return null;
    }

    /**
     * Returns the URL of a resource in the class path.
     * <p>
     * This method uses {@link ResourceManager#getResource(String)}, and have the same restriction in URL style.
     *
     * @param urlStr the URL of the resource
     * @return the URL of the resource in {@link URL} object
     * @throw MalformedURLException
     */
    public static URL getURL(String urlStr) throws MalformedURLException {
        return getResource(urlStr).getURL();
    }

    /**
     * Returns the an input stream of a resource in the class path.
     * <p>
     * This method uses {@link ResourceManager#getResource(String)}, and have the same restriction in URL style.
     *
     * @param urlStr the URL of the resource
     * @return the input stream of the resource
     * @throw IOException
     */
    public static InputStream getResourceAsInputStream(String urlStr) throws IOException {
        return getResource(urlStr).open();
    }

    public static ResourceManager get() {
        return INSTANCE;
    }

}
