@import UIKit;

@interface PreviewViewController : UIViewController
    @property (nonnull, nonatomic, copy) NSString* url;
    @property (nullable, nonatomic, copy) NSArray* previewSize;

    - (instancetype)initWithURL:(NSString *)url previewSize:(NSArray *)previewSize;
@end
