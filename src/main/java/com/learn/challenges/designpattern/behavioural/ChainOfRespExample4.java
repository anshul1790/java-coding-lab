package com.learn.challenges.designpattern.behavioural;

public class ChainOfRespExample4 {
    static class SupportRequest {
        public enum Type { TECHNICAL, BILLING, GENERAL }

        private Type type;

        public SupportRequest(Type type) {
            this.type = type;
        }

        public Type getType() {
            return type;
        }
    }

    static abstract class SupportHandler {
        // this is just like a linked list next node
        private SupportHandler next;

        public SupportHandler linkWith(SupportHandler supportHandler) {
            this.next = supportHandler;
            return supportHandler;
        }

        abstract void handleRequest(SupportRequest request);

        public void handleNext(SupportRequest request) {
            if (next != null) {
                next.handleRequest(request);
            } else {
                System.out.println("Not able to handle request");
            }
        }

    }

    static class TechSupportHandler extends SupportHandler {
        @Override
        void handleRequest(SupportRequest request) {
            if(request.getType() == SupportRequest.Type.TECHNICAL) {
                System.out.println("Handling technical support request");
            }
            handleNext(request);
        }
    }

    static class BillSupportHandler extends SupportHandler {
        @Override
        void handleRequest(SupportRequest request) {
            if(request.getType() == SupportRequest.Type.BILLING) {
                System.out.println("Handling billing support request");
            }
            handleNext(request);
        }
    }

    static class GenSupportHandler extends SupportHandler {
        @Override
        void handleRequest(SupportRequest request) {
            if(request.getType() == SupportRequest.Type.GENERAL) {
                System.out.println("Handling general support request");
            }
        }
    }

    public static void main(String[] args) {
        SupportRequest technicalRequest = new SupportRequest(SupportRequest.Type.TECHNICAL);
        SupportRequest billingRequest = new SupportRequest(SupportRequest.Type.BILLING);
        SupportRequest generalRequest = new SupportRequest(SupportRequest.Type.GENERAL);

        SupportHandler techHandler = new TechSupportHandler();
        techHandler.linkWith(new BillSupportHandler())
                .linkWith(new GenSupportHandler());

//        techHandler.handleRequest(technicalRequest);
//        techHandler.handleRequest(billingRequest);
        techHandler.handleRequest(generalRequest);
    }

}
