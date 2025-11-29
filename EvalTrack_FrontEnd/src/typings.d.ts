declare module 'pdfjs-dist' {
  export interface TextItem {
    str: string;
  }

  export interface PDFDocumentProxy {
    getPage(pageNumber: number): Promise<PDFPageProxy>;
    numPages: number;
  }

  export interface PDFPageProxy {
    getTextContent(): Promise<{
      items: TextItem[];
    }>;
  }

  export function getDocument(source: any): {
    promise: Promise<PDFDocumentProxy>;
  };

  export const GlobalWorkerOptions: {
    workerSrc: string;
  };
}