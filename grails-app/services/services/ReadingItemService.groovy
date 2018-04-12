package services

import grails.gorm.transactions.Transactional

@Transactional
class ReadingItemService {

    def serviceMethod() {

    }

    def changeIsRead(Map readingItemData){
        String str="UPDATE ReadingItem set isRead=true where id=${readingItemData.id}"
        return ReadingItem.executeUpdate(str)
    }
}
