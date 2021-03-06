import store from "../store/store"
export default {
    sanitize: function(string) {
        const map = {
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#x27;',
            "/": '&#x2F;',
        };
        const reg = /[&<>"'/]/ig;
        return string.replace(reg, (match)=>(map[match]));
    },

    toBase64(file){
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => resolve(reader.result);
            reader.onerror = error => reject(error);
        })
    },

    async processAsset(info){

        let token = store.getters.token

        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/asset`,
            {
                method: "POST",
                body: JSON.stringify(info),
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + token,
                },
                
            }
        )
            
        if(res.status === 201){
            return "Successful"
        } else if(res.status == 409){
            return "Title duplication"
        }
        return "Failed to upload"
        
    },
    async processNotice(notice, token){

        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/notice/`,
            {
                method: "POST",
                body: JSON.stringify(notice),
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + token,
                },
                
            }
        )
            
        if(res.status === 201){
            return "Successful"
        } else if(res.status == 409){
            return "Title duplication"
        }
        return "Failed to upload"
    },
    async processMeetingAlert(meetingAlert, token){

        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/meeting-alert/`,
            {
                method: "POST",
                body: JSON.stringify(meetingAlert),
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + token,
                },
                
            }
        )
            
        if(res.status === 201){
            return "Successful"
        } else if(res.status == 409){
            return "Title duplication"
        }
        return "Failed to upload"
    }
}