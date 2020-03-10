package co.rsk.net.rlpx;

import co.rsk.net.light.LightClientMessageCodes;
import co.rsk.net.light.message.GetTransactionIndex;
import co.rsk.net.light.message.TestMessage;
import co.rsk.net.light.message.TransactionIndex;
import org.ethereum.net.message.Message;

public class LCMessageFactory {
    private long id;

    public LCMessageFactory() {
        this.id = 0;
    }

    public Message create(byte code, byte[] encoded) {
        id++; //We start at 1 by doing this
        LightClientMessageCodes receivedCommand = LightClientMessageCodes.fromByte(code);
        switch (receivedCommand) {
            case TEST:
                return new TestMessage(encoded);
            case GET_TRANSACTION_INDEX:
                return GetTransactionIndex.decode(id, encoded);
            case TRANSACTION_INDEX:
                return TransactionIndex.decode(encoded);
            default:
                throw new IllegalArgumentException("No such message");
        }
    }
}
