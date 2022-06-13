package com.meshop.product.entity;

import java.util.List;

public class ProductExt extends Product{
    private int attachCount;	//첨부파일 갯수
    private List<Attachment> attachments;	//첨부파일 리스트
    private Attachment attachment;	// 대표 이미지
    
    public int getAttachCount() {
        return attachCount;
    }

    public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public void setAttachCount(int attachCount) {
        this.attachCount = attachCount;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
    
}
