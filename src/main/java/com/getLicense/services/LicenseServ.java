package com.getLicense.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getLicense.model.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class LicenseServ {

    @Value("${algorithm.name}")
    private String ALGORITHM;
    @Value("${algorithm.secret-key}")
    private String SECRET_KEY;

    @Value("${aws.bucket-name}")
    private String BUCKET_NAME;

    @Autowired
    private AmazonS3 amazonS3;

    //This function is for to get all license from the bucket
    public List<License> getAllLicenses(String softwareName, String softwareVersion, String vendorName) throws Exception {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                .withBucketName(BUCKET_NAME)
                .withPrefix("");
        List<S3ObjectSummary> objects = amazonS3.listObjects(listObjectsRequest).getObjectSummaries();
        List<License> licenses = new ArrayList<License>();
        for (S3ObjectSummary object : objects) {
            S3Object s3Object = amazonS3.getObject(BUCKET_NAME, object.getKey());
            String licenseContent = readFromS3Object(s3Object);
            byte[] encrypted = Base64.getDecoder().decode(licenseContent);
            String jsonString = decrypt(encrypted);
            ObjectMapper objectMapper = new ObjectMapper();
            License license = objectMapper.readValue(jsonString, License.class);
            if (softwareName == null || softwareName.equals(license.getSoftwareName())) {
                if (softwareVersion == null || softwareVersion.equals(license.getSoftwareVersion())) {
                    if (vendorName == null || vendorName.equals(license.getVendorName())) {
                        licenses.add(license);
                    }
                }
            }
        }
        return licenses;
    }
    //This function is for to decrypt the license
    public String decrypt(byte[] input) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decrypted = cipher.doFinal(input);
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    public String readFromS3Object(S3Object s3Object) throws IOException {
        InputStream inputStream = s3Object.getObjectContent();
        String content = new String(inputStream.readAllBytes());
        inputStream.close();
        return content;
    }
}
