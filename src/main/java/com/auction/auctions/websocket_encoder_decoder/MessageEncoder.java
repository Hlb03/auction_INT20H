//package com.auction.auctions.websocket_encoder_decoder;
//
//import com.auction.auctions.dto.representation.RepresentUserBetDTO;
//import com.google.gson.Gson;
//import jakarta.websocket.EncodeException;
//import jakarta.websocket.Encoder;
//import jakarta.websocket.EndpointConfig;
//
//public class MessageEncoder implements Encoder.Text<RepresentUserBetDTO> {
//
//    private static final Gson gson = new Gson();
//
//    @Override
//    public String encode(RepresentUserBetDTO bet) throws EncodeException {
//        return gson.toJson(bet);
//    }
//
//    @Override
//    public void init(EndpointConfig endpointConfig) {
//    }
//
//    @Override
//    public void destroy() {
//        // Close resources (if any used)
//    }
//}