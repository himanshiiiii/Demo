package linksharingapp

class ReadingItemController {

    def index() { }

    def changeIsRead(Long resourceId){
        User user = User.findById(session.userId.toLong())
        ReadingItem readingItem = ReadingItem.findByUserAndResource(user,Resource.findById(resourceId))
        if((ReadingItem.executeUpdate('update ReadingItem set isRead=:isRead where id=:id1',[isRead:readingItem.isRead,id1:resourceId]))==0){
            render("error")
        }
        else {
            render("success")
        }
    }
}
