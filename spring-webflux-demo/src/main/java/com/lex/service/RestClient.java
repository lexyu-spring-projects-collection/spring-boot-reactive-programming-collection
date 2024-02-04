package com.lex.service;

import com.lex.model.ImageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @author : Lex Yu
 */
@Slf4j
@Service
public class RestClient {

    @Autowired
    @Qualifier("compressWebClient")
    private WebClient compressWebClient;

    public Mono<ImageInfo> downloadImage(String url) throws IOException {

        Flux<DataBuffer> image_flux = compressWebClient.get()
                .uri(url)
                .accept(MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG, MediaType.APPLICATION_OCTET_STREAM)
                .exchangeToFlux(resp -> {
                    log.info("========================================================");
                    log.info("| Response Headers: {}", resp.headers().asHttpHeaders());
                    log.info("| Response Cookies = {}", resp.cookies());
                    log.info("| Response Status = {}", resp.statusCode());
                    log.info("========================================================");
                    return resp.body(BodyExtractors.toDataBuffers());
                })
                .log();

        Path path = Paths.get("spring-webflux-demo/src/main/resources/images",
                url.substring(url.lastIndexOf("/") + 1));

        Mono<byte[]> mono_bytes = DataBufferUtils.join(image_flux)
                .map(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);
                    return bytes;
                });

        return mono_bytes
                .flatMap(bytes ->
                        Mono.fromCallable(() -> {
                            Files.write(path, bytes);
                            long size = Files.size(path);
                            return ImageInfo.builder()
                                    .name(url.substring(url.lastIndexOf("/") + 1))
                                    .size(size)
                                    .msg("Download Success")
                                    .build();
                        }))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
