<template>
<div class="asset">
    <h2>{{ title }}</h2>
    <div class="asset-container">
        <div class="asset-content"> 
            <form id="create-issue-form" method="post" @submit.prevent="handleSubmit">
                <div>
                    <label for="title">Title</label>
                    <input type="text" name="title" id="title" v-model="title" required :readonly="!isEditable"/>
                </div>
                <div>
                    <label for="description">Description</label>
                    <textarea name="description" id="description" cols="30" rows="10" v-model="description" :readonly="!isEditable" required></textarea>
                </div>
                <div v-if="!isEditable">
                    <label for="sender">Sender</label>
                    <input type="text" name="sender" id="sender" v-model="senderName" readonly/>
                </div>
                <div>
                <label for="reviewDate">Reviewed by</label>
                    <input type="date" name="reviewDate" id="reviewDate" v-model="reviewDate" required :readonly="!isEditable" />
                </div>
                <div>
                    <label for="status">Asset Status</label>
                    <select name="status" id="status" v-model="status" required v-if="changeStatus || isEditable">
                        <option 
                            v-for="option in options.length" 
                            v-bind:value="options[option]" 
                            v-bind:key="option" 
                            :selected="options[option] === status"
                            :disabled="option < options.indexOf(status)"
                            
                        >
                            {{options[option]}}
                        </option>
                    </select>
                    <div v-if="!isEditable && !changeStatus" :class="status">{{ status }}</div>
                </div>
                <div>
                    <label for="recipient">Recipient(s)</label>
                    <!-- <select name="positions" id="positions" v-model="receiverNames" multiple="true" required v-if="isEditable">
                        <option v-for="option in recipients" v-bind:value="option.name" v-bind:key="option">
                            {{option.name}}
                        </option>
                    </select> -->
                    <div class="names">
                        <div v-for="name in receiverNames" :key="name" class="name">
                            {{ name }}
                        </div>
                    </div>
                </div>
                <div>
                    <label for="file">Link to File</label>
                    <input name="link"  type="url" v-model="link" :readonly="!isEditable"/>
                </div>
                <!-- <div>
                    <label for="file">File Uploaded</label>
                    <input name="file" class="custom-file-input" id="file" type="file" @change="handleFileUpload( $event )" v-if="isEditable"/>
                </div> -->
                <div class="buttons">
                    <div v-if="!(isEditable || changeStatus)" class="buttons">
                        <button @click="handleChange" id="change-status">Change Asset Status</button>
                        <button @click="handleEdit" id="edit-btn" v-if="isSender">Edit Information</button>
                    </div>
                    <div v-if="isEditable || changeStatus" class="buttons">
                        <button @click="handleCancel" id="cancel">Cancel</button>
                        <button type="submit" class="change-btn" :disabled="(file === '' && link === '')">Confirm Changes</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="feedback">
            <p class="feedback-header">Feedbacks</p>
            <div v-if="assetFeedbacks.length == 0" class="no-view">There are no feedbacks on this asset.</div>
            <div v-for="feedback in assetFeedbacks" :key="feedback.id">
                <div class="feedback-content">
                    <p class="message">{{ feedback.body }}</p>
                    <p class="msg-user">~{{ feedback.name }}</p>
                    <div class="btn">
                        <button @click="handleClick(feedback.id)" class="reply-btn">Reply</button>
                    </div>
                </div>
                <ul class="replies">
                    <li class="reply" v-for="reply in feedback.replies" :key="reply.id">
                        <p class="message">{{ reply.body }}</p>
                        <p class="msg-user">~{{ reply.name }}</p>
                    </li>
                    <li v-if="showReplyInput && replySelected === feedback.id">
                        <form id="create-feedback-form" method="post" @submit.prevent="handleFeedbackReply(feedback.id)">
                            <div>
                                <textarea name="feedback" id="feedbacktxt" cols="30" rows="10" v-model="feedbackReply" required></textarea>
                                <span>{{errorreply}}</span>
                            </div>
                            <div class="btn">
                                <button type="submit" id="post" >Reply</button>
                            </div>
                        </form>
                    </li>
                </ul>
            </div>
             <form id="create-feedback-form" method="post" @submit.prevent="handleFeedback">
                <div>
                    <textarea name="feedback" id="feedback" cols="30" rows="10" v-model="feedback" required></textarea>
                    <span>{{error}}</span>
                </div>
                <div class="btn">
                    <button type="submit" id="post">Post</button>
                </div>
             </form>
        </div>
    </div>
</div>
</template>

<script>
import store from '../store/store'
import AssetService from '../services/asset.service'


export default {
  name: 'Asset',
  data(){
      return {
        feedback: "",
        feedbackReply: "",
        changeStatus: false,
        showReplyInput: false,
        isEditable: false,
        isSender: true,
        title: "",
        description: "",
        senderName: "",
        status: "",
        file: "",
        type: "",
        link: "http",
        reviewDate: "",
        receivers: [],
        receiverNames: [],
        error: "",
        errorreply: "",
        options: ["Submitted", "Pending", "Approved", "Completed"],
        position: store.getters.position,
        recipients: store.getters.members,
        assetID: this.$route.params.id,
        assetFeedbacks: [],
        replySelected: ""
      }
  },
  beforeMount(){
    AssetService.getAsset(store.getters.token, this.assetID)
        .then(res => {
            // console.log(res)
            this.title = res.title
            this.description = res.description
            this.status = res.status.replace(/(\w)(\w*)/g,(_, firstChar, rest) => firstChar + rest.toLowerCase())
            this.link = res.assetLink
            this.isSender = store.getters.userInfo.id === res.sender.id
            this.reviewDate = res.reviewedBy
            this.senderName = res.sender.name


            let recipientsId = res.recipients.map(recipient => recipient.recipient.id)
            this.recipients.forEach(recipient => {
                if(recipientsId.includes(recipient.id)){
                    this.receiverNames.push(recipient.name)
                }
            });

        }).catch(err => console.log(err))

    AssetService.getFeedbacks(store.getters.token, this.assetID)
    .then(res => {
        res.forEach(feedback =>{
            console.log(feedback)
            // if(feedback.asset.id === this.assetID){
                this.assetFeedbacks.push(feedback)
            // }
        })
    })
    
  },
  methods: {
    handleSubmit(){
        let confirm = window.confirm("Do you want to make these changes?")
        if(confirm){
            this.recipients.forEach(recipient => {
                if(this.receiverNames.includes(recipient.name)){
                    this.receivers.push(recipient.id)
                }
            });
            let asset = {
                status: this.status.toUpperCase(),
                title: this.title,
                description: this.description,
                reviewedBy: this.reviewDate,
                assetLink: this.link,
                assetRecipients: [...this.receivers]
            }


            AssetService.uploadChanges(store.getters.token, this.assetID, asset)
                .then(res => {
                    if(res === "Title duplication"){
                        this.error = "Title already exists in the system."
                    } else {
                        if(res === "Failed to update"){
                           this.error = "Asset failed to update. Try Again."
                        }else{
                            alert("Asset was succesfully updated!")
                            this.isEditable = false
                            this.changeStatus = false
                        }
                    }
                })
        }

       
        
    },
    handleEdit(){
        this.isEditable = true
    },

    handleCancel(){
        this.isEditable = false
        this.changeStatus = false
        this.revertChanges()
    },

    handleChange(){
        this.changeStatus = true
    },

    revertChanges(){
        AssetService.getAsset(store.getters.token, this.assetID)
        .then(res => {
            this.title = res.title
            this.description = res.description
            this.status = res.status.replace(/(\w)(\w*)/g,(_, firstChar, rest) => firstChar + rest.toLowerCase())
            this.initalStatus = this.status
            this.link = res.assetLink
            this.receivers = []
            this.receiverNames = []
            this.reviewDate = res.reviewedBy
            this.isSender = store.getters.userInfo.id === res.sender.id
            let recipientsId = res.recipients.map(recipient => recipient.recipient.id)
            this.recipients.forEach(recipient => {
                if(recipientsId.includes(recipient.id)){
                    this.receiverNames.push(recipient.name)
                }
            });

        })
    },
    
    handleFeedback(){
       let feedback = { body: this.feedback, title: "", assetID:this.assetID };
       let wordCheck = this.feedback.split(" ")
        if(wordCheck.length > 500){
            this.error = "Words exceed 500"
        }
        else{
            AssetService.postFeedback(feedback, store.getters.token)
            .then(res => {
                this.error = ""
                    if(res === "Successful"){
                        this.feedback = ""
                        this.setFeedbacks()
                    } else {
                        alert("Feedback was not posted")
                    }
                
            })
        }
       

    },

    setFeedbacks(){
        AssetService.getFeedbacks(store.getters.token, this.assetID)
        .then(res => {
            this.assetFeedbacks = []
            res.forEach(feedback =>{
                this.assetFeedbacks.push(feedback)
            })
        })
    },

    handleFeedbackReply(id){
        let reply = { body : this.feedbackReply, feedbackID: id }
        let wordCheck = this.feedbackReply.split(" ")
        if(wordCheck.length > 500){
            this.errorreply = "Words exceed 500"
        }
        else{
            AssetService.postFeedbackReply(reply, store.getters.token)
            .then(res =>{
                this.errorreply = ""
                if(res === "Successful"){
                    this.feedbackReply = ""
                    this.setFeedbacks()
                }else{
                    alert("Feedback reply was not posted")
                }
            })
        }
    },
    
    handleClick(id){
        this.showReplyInput = !this.showReplyInput
        this.replySelected = id
    }

  }
}

</script>

<style scoped>
h2{
    font-weight: bolder;
    margin-bottom: 0px;
    color: #865cff;
}

label{
    font-size: 18px;
    font-weight: 700;
}

input:read-only, textarea:read-only{
    display: flex;
    font-size: 16px;
    border: none;
    color: #865cff;
    font-weight: bold;
    padding: 0;
}

textarea:read-only{
    margin-top: 5px;
    height: 50px;
}

#create-feedback-form{
     border: 1px solid rgba(0, 0,0, 0.25);
     border-radius: 10px;
     padding: 0 10px;
}

input, select, textarea{
    display: block;
    width: 95%;
    height: 40px;
    border: 1px solid #d5c7ff;
    border-radius: 8px;
    padding: 0 10px;
    font-family: Avenir, Helvetica, Arial, sans-serif;
}

.asset-content{
    border-right: 1px solid rgba(0, 0,0, 0.25);
}

.feedback{
    padding: 10px;
}

textarea{
    height: 70px;
    resize: none;
    font-size: 14px;
}

form div{
    margin: 15px 0;
}

.file-input{
    margin-bottom: 0;
}

.asset-container{
    display: grid;
    grid-template-columns: 2fr 1fr;
}

.buttons{
    display: flex;
    justify-content: flex-end;
    margin-top: -6px;
    margin-right: 10px;
}

.buttons button, #post{
    border: 1px solid #865cff;
    border-radius: 8px;
    height: 40px;
    padding: 10px 20px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0px 10px;
    background: #865cff;
    color: #ffffff;
}

button:disabled{
    background: #d5c7ff;
    border: 1px solid #d5c7ff;
}

#cancel, #change-status{
    background: none;
    color: #865cff;
}

.custom-file-input {
    border: none;
    padding-top: 10px;

}

select[multiple] {
    padding-top: 5px ;
    height: 4rem;
}

.names{
   display: flex;
}

.name{
   height: 35px;
   border: 1px solid #d5c7ff;
   border-radius: 8px;
   background-color: #d5c7ff;
   color: #865cff;
   font-weight: bold;
   padding: 0 15px;
   margin: 0 5px;
   display: flex;
   align-items: center;
   justify-content: center;
}

.feedback-content{
    border: 1px solid rgba(0, 0,0, 0.25);
    border-radius: 8px;
    padding: 4px 10px;
}

.feedback-content p{
    margin: 6px 0;
}

p{
    margin: 10px 0;
}

.msg-user{
    font-size: 12px;
    font-weight: bold;
}

.btn{
    display: flex;
    justify-content: flex-end;
}

.reply-btn{
    background: none;
    border: none;
    color: #865cff;
}

li{
    list-style: none;
    margin: 8px 0;
}

.reply{
    background: rgba(0, 0,0, 0.05);
    padding: 1px 8px;
    border-radius: 10px ;
}


.feedback-header{
    font-weight: bold;
    margin-top: -5px;
    margin-bottom: 20px;
}

span{
    color: #ff0000;
}

</style>