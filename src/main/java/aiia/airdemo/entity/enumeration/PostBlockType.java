package aiia.airdemo.entity.enumeration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PostBlockType {

    Text("Text"),
    Image("Image");

    final private String type;

}
