package link.message.client;

/** 消息类型 */
public enum MessageType {
    /** 0：控制指令 */
    CTRL(0),
    /** 1：简单文本 */
    TEXT(1),
    /** 2：图片（网盘） */
    PICUTRE(2),
    /** 3：文件（网盘） */
    FILE(3),
    /** 4：音频（网盘） */
    AUDIO(4),
    /** 5：视频（网盘） */
    VIDEO(5),
    /** 6：语音（网盘） */
    SOUND(6),
    /** 7：地理位置 */
    GIS(7),
    /** 8：事件 */
    EVENT(8),
    /** 10：分享消息 */
    SHARE(10),
    /** 11：通用格式消息-1 */
    COMMON_1(11),
    /** 12：通知消息 */
    NOTIFY(12),
    /** 13：事件 */
    EVENT_WITH_UI(13),
    /** 14：新网盘分享的消息 */
    CLOUD_DISK_SHARE(14),
    /** 15：视频聊天消息 */
    VIDEO_CHAT(15),
    /** 16：抖动消息 */
    SHAKE(16),
    /** 17：伪装消息：真正的发送者信息隐藏在content中 */
    DISGUISE(17),
    /** 18：红包消息 */
    RED_PACKET(18),
    /** 66：撤回消息 */
    ROLLBACK(66),
    /** 95：已读消息 */
    REC_READ(95),
    /** 96：混合消息 */
    MIX_TEXT(96),
    /** 97：回执消息 */
    REPLY(97),
    /** 98：失败消息 */
    FAIL(98),
    /** 99：复杂消息 */
    COMPLEX(99);

    private int value;

    MessageType(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public static MessageType valueOf(int value) {
        for (MessageType obj : values()) {
            if (obj.value == value) {
                return obj;
            }
        }
        throw new IllegalArgumentException("No enum constant " + MessageType.class.getName() + "." + value);
    }
}