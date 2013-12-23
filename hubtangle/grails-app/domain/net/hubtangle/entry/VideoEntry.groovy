package net.hubtangle.entry

import groovy.transform.EqualsAndHashCode

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Represents a video entry
 * @author mkubryn
 *
 */
@EqualsAndHashCode
class VideoEntry extends Entry {

    public static final String YOUTUBE_VIDEO_ID_REGEX = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"

	/**
	 * Location of the video. 
	 */
	String url

    static constraints = {
    }
	
	
	def getRenderTemplateName() {
		"videoEntry"
	}

    def getYoutubeVideoId() {
        String pattern = YOUTUBE_VIDEO_ID_REGEX;

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url);

        if(matcher.find()){
            return matcher.group();
        }
        return null
    }
}
