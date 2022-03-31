export default {
    toBase64(file){
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => resolve(reader.result);
            reader.onerror = error => reject(error);
        })
    },

    capitaliseFirstLetter(str){
        return str.replace(/(^|\s)\S/g, letter => letter.toUpperCase())
    },


    async getAssets(token){
        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/asset`,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            
        })
        if(res.status === 200){
            return await res.json()
        }

        // console.log(await res.json())
        return "Failed to fetch"
       
    },

    async getAsset(token, id){
        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/asset/${id}`,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            
        })
        if(res.status === 200){
            return await res.json()
        }
        return Promise.reject("Failed to fetch", res.statusText)
       
    },

    async deleteAsset(token, id){
        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/asset/${id}`,
        {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            
        })
        return res.status === 200
       
    },
    
    async uploadChanges(token, id, info){
        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/asset/${id}`,
        {
            method: "PUT",
            body: JSON.stringify(info),
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            
        })
        if(res.status === 200){
            return await res.json()
        } else if(res.status == 409){
            return "Title duplication"
        }
        return "Failed to update"
    },

    async postFeedback(feedback, token){

        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/feedback/`,
            {
                method: "POST",
                body: JSON.stringify(feedback),
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + token,
                },
                
            }
        )
            
        if(res.status === 201){
            return "Successful"
        }

        return "Failed to upload"
    },

    async getFeedbacks(token){
        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/feedback/`,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            
        })
        if(res.status === 200){
            return await res.json()
        }
        return "Failed to fetch"
       
    },

    async postFeedbackReply(reply, token, id){

        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/feedback/${id}/reply`,
            {
                method: "POST",
                body: JSON.stringify(reply),
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + token,
                },
                
            }
        )
            
        if(res.status === 200){
            return "Successful"
        }

        return "Failed to upload"
    },


}