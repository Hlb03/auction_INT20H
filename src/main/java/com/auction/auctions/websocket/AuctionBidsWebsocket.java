//package com.auction.auctions.websocket;
//
//import com.auction.auctions.dto.creation.CreateBetDTO;
//import com.auction.auctions.dto.representation.RepresentUserBetDTO;
//import com.auction.auctions.service.UserBetsService;
//import com.auction.auctions.websocket_encoder_decoder.MessageDecoder;
//import com.auction.auctions.websocket_encoder_decoder.MessageEncoder;
//import jakarta.websocket.*;
//import jakarta.websocket.server.ServerEndpoint;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
////import javax.websocket.*;
////import javax.websocket.server.PathParam;
////import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Set;
//import java.util.concurrent.CopyOnWriteArraySet;
//
//@Slf4j
//@Component
//@ServerEndpoint(
//        value = "/all-bids",
//        encoders = MessageEncoder.class,
//        decoders = MessageDecoder.class)
//@RequiredArgsConstructor
//public class AuctionBidsWebsocket {
//
//    private final UserBetsService betsService;
//
//    private Session session;
//    private static final Set<AuctionBidsWebsocket> chatEndpoints
//            = new CopyOnWriteArraySet<>();
////    private static HashMap<String, Long> auctions = new HashMap<>();
//
//
//    @OnOpen
//    public void onOpen(Session session) throws IOException {
//        this.session = session;
//        chatEndpoints.add(this);
////        auctions.put(session.getId(), auctionId);
//
//        log.info("User with session id {} has connected", session.getId());
//    }
//
//    @OnMessage
//    public void onMessage(Session session, CreateBetDTO bet) throws IOException, EncodeException {
//        broadcast(betsService.saveUserBet(bet));
//    }
//
//    @OnClose
//    public void onClose(Session session) throws IOException {
//        chatEndpoints.remove(this);
//        log.info("User with sessions id {} has disconnected", session.getId());
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        log.info("User with session id {} have an error", session.getId());
//        log.error("Stack trace: " + throwable.getCause());
//    }
//
//    private static void broadcast(RepresentUserBetDTO bet)
//            throws IOException, EncodeException {
//
//        chatEndpoints.forEach(endpoint -> {
//            synchronized (endpoint) {
//                try {
//                    endpoint.session.getBasicRemote().
//                            sendObject(bet);
//                } catch (IOException | EncodeException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//}
