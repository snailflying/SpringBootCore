package com.theone.core.util

import org.apache.commons.io.IOUtils
import org.springframework.core.io.ClassPathResource
import java.io.IOException
import java.nio.charset.Charset

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
object FileUtil {
    val comments: String?
        get() {
            var result: String? = null
            val commentsPath = "json/comments.json"
            val resource = ClassPathResource(commentsPath)

            try {
                result = IOUtils.toString(resource.inputStream, Charset.defaultCharset())
            } catch (e: IOException) {
            }

            return result
        }

    val guess: String?
        get() {
            var result: String? = null
            val commentsPath = "json/guess.json"
            val resource = ClassPathResource(commentsPath)

            try {
                result = IOUtils.toString(resource.inputStream, Charset.defaultCharset())
            } catch (e: IOException) {
            }

            return result
        }

    val others: String?
        get() {
            var result: String? = null
            val commentsPath = "json/others.json"
            val resource = ClassPathResource(commentsPath)
            try {
                result = IOUtils.toString(resource.inputStream, Charset.defaultCharset())
            } catch (e: IOException) {
            }

            return result
        }
    /*
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }*/
}
