//package com.auction.auctions.websocket_encoder_decoder;
//
//import com.auction.auctions.dto.creation.CreateBetDTO;
//import com.google.gson.Gson;
//import jakarta.websocket.DecodeException;
//import jakarta.websocket.Decoder;
//import jakarta.websocket.EndpointConfig;
//
//public class MessageDecoder implements Decoder.Text<CreateBetDTO> {
//
//    private static final Gson gson = new Gson();
//
//    @Override
//    public CreateBetDTO decode(String bet) throws DecodeException {
//        return gson.fromJson(bet, CreateBetDTO.class);
//    }
//
//    @Override
//    public boolean willDecode(String s) {
//        return (s != null);
//    }
//
//    @Override
//    public void init(EndpointConfig endpointConfig) {
//        // Custom initialization logic
//    }
//
//    @Override
//    public void destroy() {
//        // Close resources (if any used)
//    }
//}
