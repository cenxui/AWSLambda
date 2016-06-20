package handler;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

public class LambdaFunctionHandler implements RequestHandler<Map<String,String>, Map<String,String>> {

    @Override
    public Map<String,String> handleRequest(Map<String,String> input, Context context) {
        context.getLogger().log("Input: " + input);

        AmazonS3 s3 = new AmazonS3Client();
        Region apNorth1 = Region.getRegion(Regions.AP_NORTHEAST_1);
        s3.setRegion(apNorth1);
        InputStream stream = 
        		new ByteArrayInputStream(input.get("name").getBytes(StandardCharsets.UTF_8));
        ObjectMetadata mate =  new ObjectMetadata();
        mate.setContentEncoding("txt");
        s3.putObject("cenxui-lamda-function-content", "20165/256.txt", stream , mate);
       
        return input;
    }
}
		