package xmenapi

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class XmenApiController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond XmenApi.list(params), model:[xmenApiCount: XmenApi.count()]
    }

    def show(XmenApi xmenApi) {
        respond xmenApi
    }

    def create() {
        respond new XmenApi(params)
    }

    @Transactional
    def save(XmenApi xmenApi) {
        if (xmenApi == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (xmenApi.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond xmenApi.errors, view:'create'
            return
        }

        xmenApi.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'xmenApi.label', default: 'XmenApi'), xmenApi.id])
                redirect xmenApi
            }
            '*' { respond xmenApi, [status: CREATED] }
        }
    }

    def edit(XmenApi xmenApi) {
        respond xmenApi
    }

    @Transactional
    def update(XmenApi xmenApi) {
        if (xmenApi == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (xmenApi.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond xmenApi.errors, view:'edit'
            return
        }

        xmenApi.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'xmenApi.label', default: 'XmenApi'), xmenApi.id])
                redirect xmenApi
            }
            '*'{ respond xmenApi, [status: OK] }
        }
    }

    @Transactional
    def delete(XmenApi xmenApi) {

        if (xmenApi == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        xmenApi.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'xmenApi.label', default: 'XmenApi'), xmenApi.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'xmenApi.label', default: 'XmenApi'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
